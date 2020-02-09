package com.rpm.web.contents;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public interface RecentSeenCarService {

    void update(RecentSeenCar build);
}
