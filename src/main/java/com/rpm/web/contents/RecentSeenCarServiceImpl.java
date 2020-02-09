package com.rpm.web.contents;

import com.rpm.web.user.User;
import com.rpm.web.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class RecentSeenCarServiceImpl implements RecentSeenCarService{
    @Autowired
    RecentSeenCarRepository recentSeenCarRepository;
    @Autowired
    RecentSeenCar recentSeenCar;

    @Override
    public void update(RecentSeenCar recentSeenCar) {
            recentSeenCar = recentSeenCarRepository.findByUserSeqAndCid(recentSeenCar.getUserSeq() , recentSeenCar.getCid());
            recentSeenCarRepository.save(RecentSeenCar.builder()
                .userSeq(recentSeenCar.getUserSeq() )
                .cid(recentSeenCar.getCid())
                .preference( 1 )
                .build());
    }
}
