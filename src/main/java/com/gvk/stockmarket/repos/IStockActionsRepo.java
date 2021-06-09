package com.gvk.stockmarket.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gvk.stockmarket.models.StockActions;

public interface IStockActionsRepo extends JpaRepository<StockActions, Integer> {
	
	@Query(value = "select sn.id, sn.name, count(*) as total_qty, avg(sa.price) as avg_price, sn.current_price\r\n" + 
			"from stocks s, stock_names sn, stock_actions sa\r\n" + 
			"where sell_id is null and s.stock_id = sn.id and sa.id = s.buy_id and sa.stock_id = s.stock_id\r\n" + 
			"group by s.stock_id order by sn.name;", nativeQuery = true)
	public List<Object[]> getAvailableStocks();

}
