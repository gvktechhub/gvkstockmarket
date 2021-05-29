package com.gvk.stockmarket.config.batch.exceljob;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gvk.stockmarket.models.StockActions;
import com.gvk.stockmarket.services.IStockNameService;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private  IStockNameService stockNameService;
	
	@Bean
	public ItemReader<StockActions> reader() {
		return new FlatFileItemReader<StockActions>() {{
			setResource(new ClassPathResource("/datafiles/stocks.csv"));
			setLineMapper(new DefaultLineMapper<StockActions>() {{
				setLineTokenizer(new DelimitedLineTokenizer() {{
					setDelimiter(",");
					setNames("actionDate", "actionType", "stock", "quantity", "price");
				}});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<StockActions>() {{
					setTargetType(StockActions.class);
				}});
			}});
		}};
	}
	
	@Bean
	public ItemProcessor<StockActions, StockActions> processor() {
		return (record) -> {
			return record;
		};
	}
	
	@Bean
	public ItemWriter<StockActions> writer() {
		return new JdbcBatchItemWriter<StockActions>() {{
			setDataSource(datasource);
			setSql("INSERT INTO stock_actions (action_date, action_type, price, quantity, stock_id) VALUES (:action_date, :action_type, :price, :quantity, :stock_id)");
			//setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<StockActions>());
			setItemSqlParameterSourceProvider(record-> {
				 final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				 sqlParameterSource.addValue("action_date", record.getActionDate());
				 sqlParameterSource.addValue("action_type", record.getActionType());
				 sqlParameterSource.addValue("price", record.getPrice());
				 sqlParameterSource.addValue("quantity", record.getQuantity());
				 sqlParameterSource.addValue("stock_id", record.getId());
				 return sqlParameterSource;
			});
		}};
	}
	
	@Bean
	public JobExecutionListener listener() {
		return new CustomJobExecustionListener();
	}
	
	@Autowired
	public StepBuilderFactory sf;
	
	@Bean
	public Step jobStep() {
		return sf.get("jobStep")
				.<StockActions, StockActions>chunk(20)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Autowired
	public JobBuilderFactory jf;
	
	@Bean
	public Job excelJob() {
		return jf.get("excelJob")
				.listener(listener())
				.incrementer(new RunIdIncrementer())
				.start( jobStep())
				.build();
	}

}
