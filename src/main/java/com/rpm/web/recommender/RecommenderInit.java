package com.rpm.web.recommender;

import com.rpm.web.contents.*;
import com.rpm.web.magazine.ArticleRepository;
import com.rpm.web.user.User;
import com.rpm.web.user.UserRepository;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommenderInit implements ApplicationRunner {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    CarsService carsService;
    @Autowired
    List<Cars> cars;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecentSeenCarRepository recentSeenCarRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        DataModel dataModel;
        System.out.println( new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss").format (System.currentTimeMillis())+ "  INFO 18844 --- [           RecommenderInit ]         : RecommenderInit End ");
        makeADummySeenCar();
/*
        dataModel = new FileDataModel(new File("csv/recommender.csv"));


        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity( dataModel );

        GenericItemBasedRecommender genericItemBasedRecommender = new GenericItemBasedRecommender( dataModel , itemSimilarity );

        int x = 1;
        long itemId = 3332;
        List<RecommendedItem> recommendedItems = genericItemBasedRecommender.mostSimilarItems( itemId , 10);

        System.out.println("recommendedItems>>>" + recommendedItems.size());
        for (RecommendedItem recommendedItem : recommendedItems) {
            System.out.println(itemId + "," + recommendedItem.getItemID() + " , " + recommendedItem.getValue());
        }
*/

        System.out.println( new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss").format (System.currentTimeMillis())+ "  INFO 18844 --- [           RecommenderInit ]         : RecommenderInit Start ");
    }



    public void makeADummySeenCar () {

        cars = carsService.findAllByDistinct((List<Cars>) carsRepository.findAll());
        List<User> users = (List<User>) userRepository.findAll();
        System.out.println( new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss").format (System.currentTimeMillis())+ "  INFO 18844 --- [           RecommenderInit ]         : RecommenderInit Start ");

        String [] carType = {"KOR","IMP"};
        String [] categoryGroup1 = {"001","002","003"};
        String [] categoryGroup2 = {"003","004","005"};
        String [] categoryGroup3 = {"004","007","008"};
        String [] categoryGroup4 = {"007","008","010"};


        List<Cars> categoryGroup1List = cars.stream().filter( item -> item.getCarType().equals(carType[0]))
                .filter( item -> item.getCategorycd().contains(categoryGroup1[0])
                        || item.getCategorycd().contains(categoryGroup1[1])
                        || item.getCategorycd().contains(categoryGroup1[2])).collect(Collectors.toList());

        List<Cars> categoryGroup2List = cars.stream().filter( item -> item.getCarType().equals(carType[0]))
                .filter( item -> item.getCategorycd().contains(categoryGroup2[0])
                        || item.getCategorycd().contains(categoryGroup2[1])
                        || item.getCategorycd().contains(categoryGroup2[2])).collect(Collectors.toList());

        List<Cars> categoryGroup3List = cars.stream().filter( item -> item.getCarType().equals(carType[0]))
                .filter( item -> item.getCategorycd().contains(categoryGroup3[0])
                        || item.getCategorycd().contains(categoryGroup3[1])
                        || item.getCategorycd().contains(categoryGroup3[2])).collect(Collectors.toList());

        List<Cars> categoryGroup4List = cars.stream().filter( item -> item.getCarType().equals(carType[0]))
                .filter( item -> item.getCategorycd().contains(categoryGroup4[0])
                        || item.getCategorycd().contains(categoryGroup4[1])
                        || item.getCategorycd().contains(categoryGroup4[2])).collect(Collectors.toList());

        int categoryGroup1ListSize = categoryGroup1List.size();
        int categoryGroup2ListSize = categoryGroup2List.size();
        int categoryGroup3ListSize = categoryGroup3List.size();
        int categoryGroup4ListSize = categoryGroup4List.size();

        for (int i = 0; i < 100 ; i++) {
            for (int j = 0; j < categoryGroup1ListSize; j++) {
                recentSeenCarRepository.save(new RecentSeenCar()
                        .builder()
                        .userId(users.get(i).getUserid())
                        .carcd(categoryGroup1List.get((int) (Math.random() * categoryGroup1ListSize)).getCarcd())
                        .searchTime((int) (Math.random()*2))
                        .build());
            }
        }
        for (int i = 100; i < 200 ; i++) {
            for (int j = 0; j < categoryGroup2ListSize; j++) {
                recentSeenCarRepository.save(new RecentSeenCar()
                        .builder()
                        .userId(users.get(i).getUserid())
                        .carcd(categoryGroup2List.get((int) (Math.random() * categoryGroup2ListSize)).getCarcd())
                        .searchTime((int) (Math.random()*2))
                        .build());
            }
        }
        for (int i = 200; i < 400 ; i++) {
            for (int j = 0; j < categoryGroup3ListSize; j++) {
                recentSeenCarRepository.save(new RecentSeenCar()
                        .builder()
                        .userId(users.get(i).getUserid())
                        .carcd(categoryGroup3List.get((int) (Math.random() * categoryGroup3ListSize)).getCarcd())
                        .searchTime((int) (Math.random()*2))
                        .build());
            }
        }
        for (int i = 300; i < 500 ; i++) {
            for (int j = 0; j < categoryGroup4ListSize; j++) {
                recentSeenCarRepository.save(new RecentSeenCar()
                        .builder()
                        .userId(users.get(i).getUserid())
                        .carcd(categoryGroup4List.get((int) (Math.random() * categoryGroup4ListSize)).getCarcd())
                        .searchTime((int) (Math.random()*2))
                        .build());
            }
        }

        System.out.println("recentSeenCarRepository.count()>>>>" + recentSeenCarRepository.count());
    }

}
