package com.rpm.web.contents;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Table(name = "RECENTSEENCAR")
public class RecentSeenCar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "RSCID") private Long rscId;
    @NotNull @Column(name = "CID", length = 15) private Long cid;
    @Column(name = "USERSEQ", length = 50) private Long userSeq;
    @Column(name = "SEARCHTIME") private Long searchTime;
    @Column(name = "PREFERENCE") private int preference;


    @Builder
    public RecentSeenCar(
            Long cid, Long searchTime, Long userSeq, int preference
    ) {
        Assert.hasText(String.valueOf(cid), "carcd must not be empty");

        this.cid = cid;
        this.userSeq = userSeq;
        this.searchTime = searchTime;
        this.preference = preference;
    }



}
