package taba5.Artvis.Component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.List;

@Component
public class WebDriverUtil {
    String WEB_DRIVER_ID = "webdriver.chrome.driver";
    static String WEB_DRIVER_PATH = "C:\\chromedriver-win64\\chromedriver.exe";
    public static WebDriver getChromeDriver() {
        if ( ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))){
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }
        // webDriver 옵션 설정
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.setCapability("ignoreProtectedModeSettings", true);

        WebDriver driver = new ChromeDriver(chromeOptions);

        return driver;
    }
/*    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }*/
    public void crawling(WebDriver driver){
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        try {
            // Google 이미지 검색 페이지로 이동
            driver.get("https://www.google.com/imghp?hl=ko");

            // 검색어 입력
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("전시회 포스터");
            searchBox.submit();

            // 이미지 검색 결과 페이지로 로딩 대기
            Thread.sleep(3000); // 잠시 대기

            // 이미지 요소들 선택
            List<WebElement> images = driver.findElements(By.cssSelector("img.rg_i"));

            // 이미지 URL 추출 및 출력
            for (WebElement image : images) {
                String src = image.getAttribute("src");
                if (src != null && src.startsWith("http")) {
                    System.out.println(src);
                } else {
                    String dataSrc = image.getAttribute("data-src");
                    if (dataSrc != null) {
                        System.out.println(dataSrc);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
            driver.quit();
        }
    }
}
