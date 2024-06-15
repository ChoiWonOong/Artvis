package taba5.Artvis;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class ArtvisApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ArtvisApplication.class, args);

        /*String fileName = "artworkUrl.txt";
        List<String> list = new ArrayList<>();
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("http://www.google.com/imghp?hl=ko");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("미술작품");
        searchBox.submit();
        Thread.sleep(10000);
        log.info("sleep 10000");
        // 이미지 요소들 선택
        List<WebElement> images = driver.findElements(By.className("YQ4gaf"));
        log.info("images : {}", images.size());
        for (WebElement element: images) {

*//*            String data = element.getText();
            WebElement imgs = element.findElement(By.tagName("img"));*//*
            String img = element.getAttribute("src");
            log.info("img : {}", img);
            list.add(img);
            list.stream().map(r->{
                log.info("list : {}", r);
                return r;
            });
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
            for (int i=0; i<list.size(); i++) {
                writer.write(list.get(i).replace("\n","」"));
                writer.newLine();
            }
            String currentDir = System.getProperty("user.dir");
            System.out.println("파일 저장 디렉토리: " + currentDir);
            System.out.println("파일에 데이터를 성공적으로 저장했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/
    }
}
