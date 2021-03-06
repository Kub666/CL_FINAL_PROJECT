package pl.coderslab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.varia.NullAppender;
//import org.codehaus.jackson.map.annotate.JsonSerialize;

import pl.coderslab.dto.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.codehaus.jackson.map.ObjectMapper;
import pl.coderslab.entity.Game;


public class GBQuery {

    public GBQuery() {
    }

    public List<GamesSearchListElementDTO> gameFilterResult(int platform_id, LocalDate releaseDateAfter, LocalDate releaseDateBefore, int limit) {

        String url = "https://www.giantbomb.com/api/games/?api_key=a27aa22010c29559d6fec67de238b8d50337ff34&" +
                "format=json&" +
                "filter=platforms:"+platform_id+"," +
                "original_release_date:"+releaseDateAfter+"|"+releaseDateBefore+"&" +
                "limit=" + limit + "&" +
                "field_list=id,name,deck,resource_type,original_release_date";

        return getGamesSearchListElementDTOS(url);
    }

    public List<GamesSearchListElementDTO> gameSearchResult(String query, int limit) {

        String url = "https://www.giantbomb.com/api/search/?api_key=a27aa22010c29559d6fec67de238b8d50337ff34&" +
                "format=json&query="+query+"&" +
                "limit="+limit+"&resources=game&" +
                "field_list=id,name,deck,resource_type,original_release_date";

        return getGamesSearchListElementDTOS(url);
    }

    public GameDetailsDTO gameDetails(Long gbId) {

        String url = "https://www.giantbomb.com/api/game/3030-"+gbId+"/?api_key=a27aa22010c29559d6fec67de238b8d50337ff34&" +
                "format=json&" +
                "field_list=id,name,deck,description,image,original_release_date,platforms,concepts,genres,locations,objects,publishers,similar_games,themes";
        // to remove log4j warnings
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());

        GameDetailsDTO gameDTO = new GameDetailsDTO();

        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        GetMethod method = new GetMethod();
        try {
            method.setURI(new URI(url, false));
        } catch (URIException e) {
            e.printStackTrace();
        }

        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {

            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            byte[] responseBody = method.getResponseBody();

            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = new String(responseBody);
            GameResultDTO result = mapper.readValue(jsonInString, GameResultDTO.class);

            if (result.getResults() != null
                    && result.getResults().getId() != null
                    && result.getResults().getName() != null){
            gameDTO = result.getResults();} else {
                gameDTO = new GameDetailsDTO();
                gameDTO.setId(0L);
                gameDTO.setName("No data");
            }

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
        return gameDTO;

    }

    private List<GamesSearchListElementDTO> getGamesSearchListElementDTOS(String url) {
        List<GamesSearchListElementDTO> resultList = new ArrayList<>();

        // to remove log4j warnings
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());

        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod();
        try {
            method.setURI(new URI(url, false));
        } catch (URIException e) {
            e.printStackTrace();
        }

        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            byte[] responseBody = method.getResponseBody();

            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = new String(responseBody);
            GamesSearchResultDTO result = mapper.readValue(jsonInString, GamesSearchResultDTO.class);

            resultList = result.getResults();


        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {

            method.releaseConnection();
        }

        return resultList;
    }


}
