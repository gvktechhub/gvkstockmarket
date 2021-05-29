package com.gvk.stockmarket.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gvk.stockmarket.models.Stocks;

public interface IStocksRepo extends JpaRepository<Stocks, Integer> {

	@Query(value = "select * from stocks s where s.stock_id = :stockId and s.sell_id is null order by s.id limit :quantity", nativeQuery = true)
	List<Object[]> getStocksForSell(Integer stockId, Integer quantity);

	@Query(value = "select s.stock_id, sn.name, count(s.id) as quantity from stocks s,\r\n" + 
			"	 stock_actions sa, stock_names sn where s.sell_id is null and s.buy_id = sa.id\r\n" + 
			"	 and s.stock_id = sn.id and sa.stock_id = sn.id and\r\n" + 
			"	 DATEDIFF(SYSDATE(), sa.action_date) > 366 and sa.action_type = 'BUY' group by\r\n" + 
			"	 sn.id;", nativeQuery = true)
	List<Object[]> getStocksForMoreThanOneYear();

}
