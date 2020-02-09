package com.rpm.web.contents;

import org.springframework.data.repository.CrudRepository;

public interface RecentSeenCarRepository extends CrudRepository<RecentSeenCar, Long> {
    public RecentSeenCar findByUserSeqAndCid(Long userSeq, Long cid);

}
