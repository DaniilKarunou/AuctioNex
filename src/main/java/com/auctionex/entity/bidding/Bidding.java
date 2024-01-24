package com.auctionex.entity.bidding;

import com.auctionex.entity.Lot;
import com.auctionex.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Check(constraints ="status IN ('in progress', 'purchased', 'not purchased')")
@Table(name = "biddings")
public class Bidding {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "subject_id", referencedColumnName = "id")
    private Lot lotId;

    private ZonedDateTime dateStart;

    private ZonedDateTime dateEnd;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "winner", referencedColumnName = "login")
    private User winner;

    private BiddingStatus status;

    public Bidding(Lot lotId) {
        this.lotId = lotId;
        this.dateStart = ZonedDateTime.now();
        this.status = BiddingStatus.IN_PROGRESS;
    }
}
