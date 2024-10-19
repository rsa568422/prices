package com.inditex.prices.application.service;

import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.domain.service.PriceService;
import org.springframework.stereotype.Service;

@Service
public class PriceSpringService extends PriceService {

    public PriceSpringService(PriceRepository priceRepository) {
        super(priceRepository);
    }
}
