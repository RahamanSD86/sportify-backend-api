package sportify.backend.api.service;

import sportify.backend.api.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import util.JavaApiClass.iplAllMatches.IplAllMatches;


@Service
public class CricketDataService {

    @Autowired
    private WebClient webClient;

        public IplAllMatches fetchDataFromApi() {
            IplAllMatches response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/series_info")
                            .queryParam("apikey", Constants.IPL_API_KEY)
                            .queryParam("offset", "100")
                            .queryParam("id", "76ae85e2-88e5-4e99-83e4-5f352108aebc")
                            .build())
                    .retrieve()
                    .bodyToMono(IplAllMatches.class)
                    .block();  // Consider asynchronous approach for non-blocking

            System.out.println(response);
            return response;

        }
    }

