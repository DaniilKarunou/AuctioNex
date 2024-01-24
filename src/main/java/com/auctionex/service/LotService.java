package com.auctionex.service;

import com.auctionex.entity.DeliveryOption;
import com.auctionex.entity.Lot;

import java.util.List;

public interface LotService {

    /**
     * Saves a lot.
     *
     * @param lot The lot to be saved.
     * @return The saved lot.
     */
    Lot saveLot(Lot lot);

    /**
     * Finds a lot by its lot number.
     *
     * @param id The lot id of the lot to be found.
     * @return The found lot or null if not found.
     */
    Lot findLotById(Integer id);

    /**
     * Retrieves all lots.
     *
     * @return An iterable collection of all lots.
     */
    Iterable<Lot> getAllLots();

    /**
     * Retrieves lots with pagination.
     *
     * @param pageNr        The page number.
     * @param howManyOnPage The number of lots on each page.
     * @return An iterable collection of lots for the specified page.
     */
    Iterable<Lot> getAllLotsPaging(Integer pageNr, Integer howManyOnPage);

    /**
     * Deletes a lot by its lot number.
     *
     * @param lotNumber The lot number of the lot to be deleted.
     */
    void deleteLot(Integer lotNumber);

    /**
     * Retrieves delivery options associated with a lot.
     *
     * @param id The ID of the lot for which delivery options are retrieved.
     * @return A list of delivery options associated with the lot.
     */
    List<DeliveryOption> getDeliveryOptionsByLotId(Integer id);
}