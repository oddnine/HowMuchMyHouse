package toyproject.hmmh.configure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toyproject.hmmh.api.GetApi;
import toyproject.hmmh.getdata.GetTxtData;
import toyproject.hmmh.item.AptRepository;
import toyproject.hmmh.item.AptRepositoryImpl;
import toyproject.hmmh.item.RegionCodeItemRepository;
import toyproject.hmmh.item.RegionCodeItemRepositoryImpl;
import toyproject.hmmh.service.AptService;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final DataSource dataSource;

    @Bean
    public GetTxtData getTxtData() {
        return new GetTxtData();
    }

    @Bean
    public RegionCodeItemRepository regionCodeItemRepository() {
        return new RegionCodeItemRepositoryImpl();
    }

    @Bean
    public AptRepository aptRepository() {
        return new AptRepositoryImpl(dataSource);
    }

}
