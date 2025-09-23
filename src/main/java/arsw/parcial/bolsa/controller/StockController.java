package arsw.parcial.bolsa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arsw.parcial.bolsa.model.Stock;
import arsw.parcial.bolsa.service.StockProviderService;

@RestController
public class StockController {
    @Autowired
    @Qualifier("alphaVantageStockService")
    private StockProviderService stockProviderService;

    @GetMapping("/stocks/{symbol}")
    public ResponseEntity<Stock> getStock(
            @PathVariable String symbol,
            @RequestParam(defaultValue = "intra") String type,
            @RequestParam(defaultValue = "5min") String interval) {
        try {
            Stock stock = stockProviderService.fetchStockData(symbol, type, interval);
            return ResponseEntity.ok(stock);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
