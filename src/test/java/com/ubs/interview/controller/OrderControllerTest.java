package com.ubs.interview.controller;

import com.ubs.interview.dao.HardcodedProductEnum;
import com.ubs.interview.domain.Portfolio;
import com.ubs.interview.domain.ProductStock;
import com.ubs.interview.service.OrderManagementService;
import com.ubs.interview.view.Trade;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.ubs.interview.dao.HardcodedProductEnum.ABBA_RECORD;
import static com.ubs.interview.dao.HardcodedProductEnum.DARK_CHOCOLATE;
import static com.ubs.interview.dao.HardcodedProductEnum.GOLD;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @Test
    public void canShowOrdersPage() throws Exception {

        // given
        final String user = "user";
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final Portfolio companyPortfolio = mock(Portfolio.class, "companyPortfolio");
        when(orderManagementService.getAvailableProducts()).thenReturn(companyPortfolio);
        final Portfolio userPortfolio = mock(Portfolio.class, "userPortfolio");
        when(orderManagementService.getPortfolio(user)).thenReturn(userPortfolio);
        final OrderController controller = new OrderController(orderManagementService);
        final HttpServletRequest httpServletRequest = mockHttpServletRequest(user);
        final Map<String, Object> model = new HashMap<>();

        // when
        final String viewName = controller.showOrdersPage(httpServletRequest, model);

        // then
        assertThat(viewName, equalTo("orders"));
        assertThat(model.get("portfolio"), equalTo(companyPortfolio));
        assertThat(model.get("userPortfolio"), equalTo(userPortfolio));
    }

    @Test
    public void canShowTradeProductForm() throws Exception {

        // given
        final String user = "user";
        final String productId = "someProduct";
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final Portfolio companyPortfolio = mock(Portfolio.class, "companyPortfolio");
        when(orderManagementService.getAvailableProducts()).thenReturn(companyPortfolio);
        final ProductStock productStock = mock(ProductStock.class);
        when(orderManagementService.getProductStock(user, productId)).thenReturn(productStock);
        final OrderController controller = new OrderController(orderManagementService);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteUser()).thenReturn(user);
        final Map<String, Object> model = new HashMap<>();

        // when
        final String viewName = controller.showTradeProductForm(productId, request, model);

        // then
        assertThat(viewName, equalTo("tradeProduct"));
        assertThat(model.get("trade"), equalTo(new Trade()));
        assertThat(model.get("portfolio"), equalTo(companyPortfolio));
        assertThat(model.get("productStock"), equalTo(productStock));
    }

    @Test
    public void canExecuteTrade() throws Exception {

        // given
        final String user = "user";
        final String sellProduct = ABBA_RECORD.getProduct().getId();
        final String buyProduct = DARK_CHOCOLATE.getProduct().getId();
        final HttpServletRequest request = mockHttpServletRequest(user);
        final Map<String, Object> model = new HashMap<>();
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final Portfolio companyPortfolio = mock(Portfolio.class, "companyPortfolio");
        when(orderManagementService.getAvailableProducts()).thenReturn(companyPortfolio);
        final Portfolio userPortfolio = mock(Portfolio.class, "userPortfolio");
        when(orderManagementService.getPortfolio(user)).thenReturn(userPortfolio);
        final OrderController controller = new OrderController(orderManagementService);
        final Trade trade = new Trade();
        trade.setBuyProduct(buyProduct);
        trade.setBuyAmount(1);
        trade.setSellAmount(1);
        final Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(false);

        // when
        final String viewName = controller.executeTrade(sellProduct, request, model, trade, errors);

        // then
        verify(orderManagementService).executeClientTrade(user, "ubs", sellProduct, buyProduct, trade.getSellAmount(), trade.getBuyAmount());
        assertThat(viewName, equalTo("orders"));
        assertThat(model.get("portfolio"), equalTo(companyPortfolio));
        assertThat(model.get("userPortfolio"), equalTo(userPortfolio));
    }

    private HttpServletRequest mockHttpServletRequest(String user) {
        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRemoteUser()).thenReturn(user);
        return httpServletRequest;
    }
}