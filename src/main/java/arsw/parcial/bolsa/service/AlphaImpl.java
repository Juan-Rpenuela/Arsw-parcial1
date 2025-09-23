package arsw.parcial.bolsa.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import arsw.parcial.bolsa.model.Stock;

@Service("alphaVantageStockService")
public class AlphaImpl implements StockProviderService {
	@Value("${alpha.vantage.api.key}")
	private String apiKey;

	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public AlphaImpl() {
		this.httpClient = HttpClient.newHttpClient();
		this.objectMapper = new ObjectMapper();
	}

	public Stock fetchStockData(String symbol, String type, String interval) throws Exception {
		String function;
		String url;
		switch (type.toLowerCase()) {
			case "intra":
				function = "TIME_SERIES_INTRADAY";
				url = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&interval=%s&apikey=%s", function, symbol, interval, apiKey);
				break;
			case "daily":
				function = "TIME_SERIES_DAILY";
				url = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=%s", function, symbol, apiKey);
				break;
			case "weekly":
				function = "TIME_SERIES_WEEKLY";
				url = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=%s", function, symbol, apiKey);
				break;
			case "monthly":
				function = "TIME_SERIES_MONTHLY";
				url = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=%s", function, symbol, apiKey);
				break;
			default:
				throw new IllegalArgumentException("Tipo de histórico no soportado: " + type);
		}
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		return objectMapper.readValue(response.body(), Stock.class);
	}
}
    
