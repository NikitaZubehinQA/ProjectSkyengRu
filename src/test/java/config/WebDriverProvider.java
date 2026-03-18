package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class WebDriverProvider {

    public static WebDriverConfig config;

    public static void setConfig() {
        String env = System.getProperty("env");
        if (env == null || env.isBlank()) {
            System.setProperty("env", "local");
        }
        config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

        Configuration.baseUrl = config.getBaseUrl();

        String browserName = config.getBrowserName();
        if (browserName == null || browserName.isBlank()) {
            browserName = "chrome";
        }
        Configuration.browser = browserName;

        String browserVersion = config.getBrowserVersion();
        if (browserVersion != null && !browserVersion.isBlank()) {
            Configuration.browserVersion = browserVersion;
        }

        String browserSize = config.getBrowserSize();
        if (browserSize != null && !browserSize.isBlank()) {
            Configuration.browserSize = browserSize;
        }

        String remoteUrl = config.getRemoteUrl();
        if (remoteUrl != null && !remoteUrl.isBlank()) {
            Configuration.remote = remoteUrl;

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", selenoidOptions);
            Configuration.browserCapabilities = capabilities;
        }
    }
}
