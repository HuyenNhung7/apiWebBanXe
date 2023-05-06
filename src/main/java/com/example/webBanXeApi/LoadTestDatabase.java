package com.example.webBanXeApi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.webBanXeApi.repositories.*;
import com.example.webBanXeApi.models.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class LoadTestDatabase implements CommandLineRunner {

    private final XeRepository xeRepository;
    private final NewsRepository newsRepository;

    public LoadTestDatabase(XeRepository xeRepository, NewsRepository newsRepository) {
        this.xeRepository = xeRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long xeCount = xeRepository.count();
        long newsCount = newsRepository.count();

// Check if the count is greater than 0, indicating that the table is not empty
        if (xeCount <= 0) {
            // Mockup data for Xe model
            Xe xe1 = new Xe("Xe A", "Thuong Hieu A", "Dong Co A", 4, "Kich Thuoc A", "Nguon Goc A", "Van Toc Toi Da A", "Dung Tich A", "Tieu Hao Nhien Lieu A", "Cong Suat Cuc Dai A", "Mau Sac A", 1500000.0, "Hinh Anh A", "Mo Ta A", 2018, 5, false);
            Xe xe2 = new Xe("Xe B", "Thuong Hieu B", "Dong Co B", 5, "Kich Thuoc B", "Nguon Goc B", "Van Toc Toi Da B", "Dung Tich B", "Tieu Hao Nhien Lieu B", "Cong Suat Cuc Dai B", "Mau Sac B", 1800000.0, "Hinh Anh B", "Mo Ta B", 2020, 3, true);
            Xe xe3 = new Xe("Xe C", "Thuong Hieu C", "Dong Co C", 7, "Kich Thuoc C", "Nguon Goc C", "Van Toc Toi Da C", "Dung Tich C", "Tieu Hao Nhien Lieu C", "Cong Suat Cuc Dai C", "Mau Sac C", 2000000.0, "Hinh Anh C", "Mo Ta C", 2019, 10, false);
            Xe xe4 = new Xe("Xe D", "Thuong Hieu D", "Dong Co D", 6, "Kich Thuoc D", "Nguon Goc D", "Van Toc Toi Da D", "Dung Tich D", "Tieu Hao Nhien Lieu D", "Cong Suat Cuc Dai D", "Mau Sac D", 1700000.0, "Hinh Anh D", "Mo Ta D", 2017, 2, true);
            Xe xe5 = new Xe("Xe E", "Thuong Hieu E", "Dong Co E", 4, "Kich Thuoc E", "Nguon Goc E", "Van Toc Toi Da E", "Dung Tich E", "Tieu Hao Nhien Lieu E", "Cong Suat Cuc Dai E", "Mau Sac E", 1600000.0, "Hinh Anh E", "Mo Ta E", 2016, 8, false);
            Xe xe6 = new Xe("Xe F", "Thuong Hieu F", "Dong Co F", 5, "Kich Thuoc F", "Nguon Goc F", "Van Toc Toi Da F", "Dung Tich F", "Tieu Hao Nhien Lieu F", "Cong Suat Cuc Dai F", "Mau Sac F", 1900000.0, "Hinh Anh F", "Mo Ta F", 2021, 6, true);
            Xe xe7 = new Xe("Xe G", "Thuong Hieu G", "Dong Co G", 7, "Kich Thuoc G", "Nguon Goc G", "Van Toc Toi Da G", "Dung Tich G", "Tieu Hao Nhien Lieu G", "Cong Suat Cuc Dai G", "Mau Sac G", 2200000.0, "Hinh Anh G", "Mo Ta G", 2019, 4, false);
            Xe xe8 = new Xe("Xe H", "Thuong Hieu H", "Dong Co H", 6, "Kich Thuoc H", "Nguon Goc H", "Van Toc Toi Da H", "Dung Tich H", "Tieu Hao Nhien Lieu H", "Cong Suat Cuc Dai H", "Mau Sac H", 2100000.0, "Hinh Anh H", "Mo Ta H", 2022, 7, true);
            Xe xe9 = new Xe("Xe I", "Thuong Hieu I", "Dong Co I", 4, "Kich Thuoc I", "Nguon Goc I", "Van Toc Toi Da I", "Dung Tich I", "Tieu Hao Nhien Lieu I", "Cong Suat Cuc Dai I", "Mau Sac I", 1700000.0, "Hinh Anh I", "Mo Ta I", 2018, 9, false);
            Xe xe10 = new Xe("Xe J", "Thuong Hieu J", "Dong Co J", 5, "Kich Thuoc J", "Nguon Goc J", "Van Toc Toi Da J", "Dung Tich J", "Tieu Hao Nhien Lieu J", "Cong Suat Cuc Dai J", "Mau Sac J", 1800000.0, "Hinh Anh J", "Mo Ta J", 2020, 5, true);
            xeRepository.saveAll(List.of(xe1,xe2,xe3,xe4,xe5,xe6,xe7,xe8,xe9,xe10));
            System.out.println("Mockup Xe data loaded successfully!");
        }
        if (newsCount <= 0) {
            // mockup data about cars for News class
            for (int i = 0; i < 100; i++) {
                Faker faker = new Faker();
                News news = new News();
                news.setTitle(faker.leagueOfLegends().quote());
                news.setContent(faker.company().name() + " is introducing a long-promised " + faker.dune().planet() + " package for " + faker.dragonBall().character() + ", promising to finally unlock the electric sedan's full performance capacity.");
                news.setAuthor(faker.name().fullName());
                news.setDate(faker.date().birthday());
                news.setImage("https://generatorfun.com/code/uploads/Random-Car-image-"+ faker.random().nextInt(1,19) + ".jpg");
                news.setCategory(faker.hacker().noun());
                newsRepository.save(news);
            }


            System.out.println("Mockup News data loaded successfully!");
        }
    }
}
