package com.ubs.interview.controller;

import com.ubs.interview.dao.HardcodedProductEnum;
import com.ubs.interview.service.OrderManagementService;
import com.ubs.interview.util.TradeUtils;
import com.ubs.interview.view.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class OrderController {

    private final OrderManagementService orderManagementService;

    @Autowired
    public OrderController(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrdersPage(HttpServletRequest request, Map<String, Object> model) {

        model.put("portfolio", orderManagementService.getAvailableProducts());
        model.put("userPortfolio", orderManagementService.getPortfolio(request.getRemoteUser()));

        return "orders";
	}

    @RequestMapping(value = "/orders/trade/{productId}", method = RequestMethod.GET)
	public String showTradeProductForm(
	        @PathVariable String productId,
            HttpServletRequest request,
            Map<String, Object> model) {

        model.put("portfolio", orderManagementService.getAvailableProducts());
        model.put("productStock", orderManagementService.getProductStock(request.getRemoteUser(), productId));
        model.put("trade", new Trade());

        return "tradeProduct";
	}

    @RequestMapping(value = "/orders/trade/{productId}", method = RequestMethod.POST)
	public String executeTrade(
            @PathVariable String productId,
            HttpServletRequest request,
            Map<String, Object> model,
            @ModelAttribute("trade") Trade trade,
            Errors errors) {

        validateTrade(trade, productId, errors);
        if (errors.hasErrors()) {
            model.put("portfolio", orderManagementService.getAvailableProducts());
            model.put("productStock", orderManagementService.getProductStock(request.getRemoteUser(), productId));
            return "tradeProduct";
        }

        final String user = request.getRemoteUser();
        orderManagementService.executeClientTrade(
                user,
                "ubs",
                productId,
                trade.getBuyProduct(),
                trade.getSellAmount(),
                trade.getSellAmount());

        model.put("portfolio", orderManagementService.getAvailableProducts());
        model.put("userPortfolio", orderManagementService.getPortfolio(request.getRemoteUser()));

        return "orders";
	}

    private void validateTrade(Trade trade, String sellProductId, Errors errors) {

        if (StringUtils.isEmpty(trade.getSellAmount())) {
            errors.rejectValue("sellAmount", "NotEmpty.trade.sellAmount");
        }

        if (StringUtils.isEmpty(trade.getBuyAmount())) {
            errors.rejectValue("buyAmount", "NotEmpty.trade.buyAmount");
        }

        if (StringUtils.isEmpty(trade.getBuyProduct())) {
            errors.rejectValue("buyProduct", "NotEmpty.trade.buyProduct");
        }

        if (trade.getSellAmount() <= 0) {
            errors.rejectValue("sellAmount", "AtLeastOne.trade.sellAmount");
        }

        if (trade.getBuyAmount() <= 0) {
            errors.rejectValue("buyAmount", "AtLeastOne.trade.buyAmount");
        }

        if (StringUtils.hasText(trade.getBuyProduct())) {
            try {
                HardcodedProductEnum.getProductById(trade.getBuyProduct());
                if (!TradeUtils.isValidPriceMatch(sellProductId, trade.getBuyProduct(), trade.getSellAmount(), trade.getBuyAmount())) {
                    errors.rejectValue("buyAmount", "TradeNotValid.trade.buyAmount");
                }
            } catch (Exception e) {
                errors.rejectValue("buyProduct", "NoSuchProduct.trade.buyProduct");
            }
        }
}
    }
