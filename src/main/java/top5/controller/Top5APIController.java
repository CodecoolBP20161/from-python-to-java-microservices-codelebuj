package top5.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import top5.dao.implement.ClientDaoJdbc;
import top5.dao.implement.PaidProductDaoJdbc;
import top5.model.PaidProducts;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Top5APIController {
    private static final Logger logger = LoggerFactory.getLogger(Top5APIController.class);
    private static ClientDaoJdbc clientDao = ClientDaoJdbc.getInstance();
    private static PaidProductDaoJdbc paidProductDao = PaidProductDaoJdbc.getInstance();

    public String addProduct(Request request, Response response) throws IOException {
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        //String jsonInString = request.body();
        JSONArray jsonArray = new JSONArray(request.body());
        if (!clientDao.findClient(request.params(":apikey")).equals(null)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                paidProductDao.addPaidProducts((new PaidProducts(jsonArray.getJSONObject(i).getInt("id"), jsonArray.getJSONObject(i).getInt("quantity"), date, request.params(":apikey"))));
            }
        }
/*        Gson gson = new Gson();
        PaidProducts data= gson.fromJson(jsonInString, PaidProducts.class);


        if (!clientDao.findClient(request.params(":apikey")).equals(null)){
            paidProductDao.addPaidProducts(new PaidProducts(data.getProductID(),data.getQuantity(), date, request.params(":apikey")));
        }*/
        return "OK";
    }

    public String getTop5(Request request, Response response) throws IOException {
        List<PaidProducts> details =  paidProductDao.findPaidProducts(request.params(":apikey"));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(details);
    }


    public String status(Request request, Response response) throws IOException {
        return "ok";
    }

}
