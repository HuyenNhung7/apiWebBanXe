package com.example.webBanXeApi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.webBanXeApi.repositories.*;
import com.example.webBanXeApi.models.*;
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
            News news1 = new News();
            news1.setTitle("Tesla launches Model 3 in India with starting price of Rs 55 lakh");
            news1.setContent("Tesla has finally launched its most affordable electric car, the Model 3, in India with a starting price of Rs 55 lakh (ex-showroom). The Model 3 is available in two variants: Standard Range Plus and Long Range. The Standard Range Plus variant has a range of 423 km on a single charge and can accelerate from 0 to 100 km/h in 5.6 seconds. The Long Range variant has a range of 568 km and can sprint from 0 to 100 km/h in 4.4 seconds. Both variants have a top speed of 225 km/h. The Model 3 comes with a 15-inch touchscreen that controls most of the functions of the car, such as navigation, music, climate, and settings. The car also has a full glass roof, wireless charging, and a spacious boot. The Model 3 is equipped with Tesla's Autopilot system, which can assist the driver with steering, braking, and lane changing. The car also has access to Tesla's Supercharger network, which can charge the battery up to 80% in 30 minutes.");
            news1.setAuthor("Elon Musk");
            news1.setDate(new Date(2023, Calendar.MAY, 25));
            news1.setImage("https://cdn.motor1.com/images/mgl/6wL6x/s1/tesla-model-3.jpg");
            news1.setCategory("Automobile");

            News news2 = new News();
            news2.setTitle("Toyota unveils hydrogen-powered Mirai with futuristic design and zero emissions");
            news2.setContent("Toyota has unveiled its second-generation hydrogen-powered car, the Mirai, which features a futuristic design and zero emissions. The Mirai, which means 'future' in Japanese, is powered by a fuel cell that converts hydrogen into electricity and water. The car has a range of 650 km on a full tank of hydrogen and can be refueled in less than five minutes. The Mirai has a sleek and aerodynamic body that reduces drag and improves efficiency. The car has a spacious cabin that can accommodate five passengers and a large trunk. The car also has a 12.3-inch touchscreen that displays information such as speed, battery level, and navigation. The car also has advanced safety features such as adaptive cruise control, lane keep assist, and blind spot monitor. The Mirai is expected to go on sale in Japan later this year and in other markets next year.");
            news2.setAuthor("Akio Toyoda");
            news2.setDate(new Date(2023, Calendar.MAY, 25));
            news2.setImage("https://www.toyota.com/content/dam/toyota-cms/homepage/2020/mirai/mirai-2020-og.jpg");
            news2.setCategory("Environment");

            News news4 = new News();
            news4.setTitle("Maruti Suzuki launches Celerio X with sporty design and enhanced features");
            news4.setContent("Maruti Suzuki has launched its new hatchback, the Celerio X, with a sporty design and enhanced features. The Celerio X is based on the Celerio but has a more aggressive and stylish look. The car has a black grille, bumper, and cladding, along with a roof rail and a rear spoiler. The car also has dual-tone alloy wheels and LED headlamps. The Celerio X is available in six colors: orange, blue, white, silver, gray, and green. The Celerio X has a spacious and comfortable cabin that can seat five people. The car has a 7-inch touchscreen infotainment system that supports Android Auto and Apple CarPlay. The car also has a multi-function steering wheel, automatic climate control, and rear parking sensors. The Celerio X is powered by a 1.0-liter petrol engine that produces 68 PS of power and 90 Nm of torque. The car has a fuel efficiency of 21.63 kmpl and can run on both petrol and CNG. The car has a manual and an automatic transmission option.");
            news4.setAuthor("R.C. Bhargava");
            news4.setDate(new Date(2023, Calendar.MAY, 25));
            news4.setImage("https://imgd.aeplcdn.com/1056x594/cw/ec/41428/Maruti-Suzuki-Celerio-X-Exterior-170055.jpg?wm=0&q=85");
            news4.setCategory("Automobile");

            News news5 = new News();
            news5.setTitle("Hyundai reveals Ioniq 5 electric SUV with futuristic design and fast charging");
            news5.setContent("Hyundai has revealed its first electric SUV under its Ioniq sub-brand, the Ioniq 5, which features a futuristic design and fast charging. The Ioniq 5 is based on Hyundai's 45 concept car that was showcased in 2019. The car has a sharp and angular body that gives it a distinctive look. The car has a clamshell hood, pixel-inspired LED lights, flush door handles, and a solar roof. The car also has 20-inch alloy wheels that are the largest in its segment. The Ioniq 5 has a spacious and modular interior that can be customized according to the user's preference. The car has a sliding center console, reclining seats, a flat floor, and a large trunk. The car also has a dual-screen dashboard that consists of a 12-inch digital instrument cluster and a 12-inch touchscreen infotainment system. The car also has wireless charging, head-up display, and ambient lighting. The Ioniq 5 is powered by an electric motor that can deliver up to 306 PS of power and 605 Nm of torque. The car has a battery pack that can store up to 72 kWh of energy and can offer a range of up to 480 km on a single charge. The car also supports fast charging that can charge the battery from 10% to 80% in just 18 minutes.");
            news5.setAuthor("Euisun Chung");
            news5.setDate(new Date(2023, Calendar.MAY, 25));
            news5.setImage("https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/images/car-reviews/first-drives/legacy/hyundai-ioniq-5-2021-fd-hero-front.jpg?itok=Z6zFZk7f");
            news5.setCategory("Environment");
            newsRepository.saveAll(List.of(news1,news2, news4, news5));
            System.out.println("Mockup News data loaded successfully!");
        }
    }
}
