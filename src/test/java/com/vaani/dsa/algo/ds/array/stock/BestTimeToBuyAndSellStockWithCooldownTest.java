package com.vaani.dsa.algo.ds.array.stock;

import com.vaani.dsa.algo.ds.array.stock.BestTimeToBuyAndSellStockWithCooldown;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BestTimeToBuyAndSellStockWithCooldownTest {
    BestTimeToBuyAndSellStockWithCooldown underTest;

    @BeforeEach
    void setUp() {
        underTest = new BestTimeToBuyAndSellStockWithCooldown();
    }

    @AfterEach
    void tearDown() {
        underTest = null;
    }

    @Test
    void maxProfit() {
        Assert.assertEquals(3, underTest.maxProfit(new int[]{1, 2, 3, 0, 2}));
    }
}