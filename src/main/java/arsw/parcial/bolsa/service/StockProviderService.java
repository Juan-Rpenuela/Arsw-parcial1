package arsw.parcial.bolsa.service;

import arsw.parcial.bolsa.model.Stock;

public interface StockProviderService {
    Stock fetchStockData(String symbol, String type, String interval) throws Exception;
}