package arsw.parcial.bolsa.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    @JsonProperty("Meta Data")
    private HashMap<String, String> metaData;

    @JsonProperty("Time Series (5min)")
    @JsonAlias({
        "Time Series (1min)",
        "Time Series (15min)",
        "Time Series (30min)",
        "Time Series (60min)",
        "Time Series (Daily)",
        "Time Series (Weekly)",
        "Time Series (Monthly)"
    })
    private HashMap<String, Map<String, String>> timeSeriesRaw;

    public Stock() {}

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(HashMap<String, String> metaData) {
        this.metaData = metaData;
    }

    public Map<String, Map<String, String>> getTimeSeriesRaw() {
        return timeSeriesRaw;
    }

    public void setTimeSeriesRaw(HashMap<String, Map<String, String>> timeSeriesRaw) {
        this.timeSeriesRaw = timeSeriesRaw;
    }

}
