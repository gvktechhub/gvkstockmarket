package com.gvk.stockmarket.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gvk.stockmarket.models.Stocks;

public interface IStocksRepo extends JpaRepository<Stocks, Integer> {
	
	 String baseQuery = "select s.stock_id, sn.name, count(s.id) as quantity, REPLACE(FORMAT(avg(sa.price),3), ',', '') as avgprice, \r\n" + 
			"			REPLACE(FORMAT(sn.current_price,3), ',', '') as current_price, REPLACE(FORMAT((sn.current_price - avg(sa.price)) * count(s.id),3), ',', '') as profit_or_loss_amount,  \r\n" + 
			"			REPLACE(FORMAT((sn.current_price - avg(sa.price))*100/avg(sa.price),3), ',', '') as profit_or_loss__percent\r\n" + 
			"			from stocks s, stock_actions sa, stock_names sn \r\n" + 
			"			where s.sell_id is null and s.buy_id = sa.id and s.stock_id = sn.id and sa.stock_id = sn.id \r\n" + 
			"			 and sa.action_type = 'BUY'\r\n" + 
			"			 group by sn.id" ;

	@Query(value = "select * from stocks s where s.stock_id = :stockId and s.sell_id is null order by s.id limit :quantity", nativeQuery = true)
	List<Object[]> getStocksForSell(Integer stockId, Integer quantity);

	@Query(value = "select s.stock_id, sn.name, count(s.id) as quantity, REPLACE(FORMAT(avg(sa.price),3), ',', '') as avgprice, \r\n" + 
			"REPLACE(FORMAT(sn.current_price,3), ',', '') as current_price, REPLACE(FORMAT((sn.current_price - avg(sa.price)) * count(s.id),3), ',', '') as profit_or_loss_amount,  \r\n" + 
			"REPLACE(FORMAT((sn.current_price - avg(sa.price))*100/avg(sa.price),3), ',', '') as profit_or_loss__percent\r\n" + 
			"from stocks s, stock_actions sa, stock_names sn \r\n" + 
			"where s.sell_id is null and s.buy_id = sa.id and s.stock_id = sn.id and sa.stock_id = sn.id \r\n" + 
			"and DATEDIFF(SYSDATE(), sa.action_date) > 366 and sa.action_type = 'BUY'\r\n" + 
			" group by sn.id;", nativeQuery = true)
	List<Object[]> getStocksForMoreThanOneYear();
	
	@Query(value = "select s.stock_id, sn.name, count(s.id) as quantity, REPLACE(FORMAT(avg(sa.price),3), ',', '') as avgprice, \r\n" + 
			"REPLACE(FORMAT(sn.current_price,3), ',', '') as current_price, REPLACE(FORMAT((sn.current_price - avg(sa.price)) * count(s.id),3), ',', '') as profit_or_loss_amount,  \r\n" + 
			"REPLACE(FORMAT((sn.current_price - avg(sa.price))*100/avg(sa.price),3), ',', '') as profit_or_loss__percent\r\n" + 
			"from stocks s, stock_actions sa, stock_names sn \r\n" + 
			"where s.sell_id is null and s.buy_id = sa.id and s.stock_id = sn.id and sa.stock_id = sn.id \r\n" + 
			"and DATEDIFF(SYSDATE(), sa.action_date) < 366 and DATEDIFF(SYSDATE(), sa.action_date) > 336 and sa.action_type = 'BUY'\r\n" + 
			" group by sn.id;", nativeQuery = true)
	List<Object[]> getStocksForUpcomingMoreThanOneYear();
	
	@Query(value = baseQuery + " order by (sn.current_price - avg(sa.price))*100/avg(sa.price) desc limit 10", nativeQuery = true)
	List<Object[]> getTopProfitableStocks();
	
	@Query(value = baseQuery + " order by (sn.current_price - avg(sa.price))*100/avg(sa.price) asc limit 10", nativeQuery = true)
	List<Object[]> getTopUnprofitableStocks();

}
