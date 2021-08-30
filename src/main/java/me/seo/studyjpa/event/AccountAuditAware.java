package me.seo.studyjpa.event;

import me.seo.studyjpa.domain.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // 빈으로 등록 class name 그대로, 첫글자 소문자
// AccountAuditAware를 EnableJpaAuditing에 설정을 해주어야 한다 // 빈 이름으로 설정
public class AccountAuditAware implements AuditorAware<Account> {

    // spring 시큐리티에서 user 가져와서 설정을 하는 것
    // but 시큐리티 배우지 않았으므로 확인만...
    // 같은 쓰레드 안에 있으면 쓰레드 안에 유저정보 참조 가능

    @Override
    public Optional<Account> getCurrentAuditor() {
        System.out.println("looking for current user");
        return Optional.empty();
    }
}
