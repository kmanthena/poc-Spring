import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import com.nisum.domain.Order;
import com.nisum.domain.OrderHeader;
import com.nisum.domain.OrderLine;
import com.nisum.repository.OrderHeaderInfo;
import com.nisum.repository.OrderLineInfo;
import com.nisum.services.OrderService;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	//@InjectMocks annotation is used to create and inject the mock object
	@InjectMocks 
	OrderService orderService = new OrderService();

	@Mock
	OrderHeaderInfo orderHeaderInfo;
	@Mock
	OrderLineInfo orderLineInfo;
	
    @Before 
    public void dataSetUpForValidateUniqueOrderNo() throws Exception { 
        
    	OrderHeader oHeader = new OrderHeader();
    	oHeader.setStrFirstName("Kishore");
    	oHeader.setStrLastName("Kishore");
    	oHeader.setStrOrderNo("1");
    	oHeader.setStrPaymentInfo("Card");
    	
        when(orderHeaderInfo.findOne("1")).thenReturn(oHeader);
        when(orderHeaderInfo.findOne("2")).thenReturn(null);
    } 
	
    @Before 
    public void dataSetUpForCreateNewOrder() throws Exception { 
        
    	OrderHeader oHeader = new OrderHeader();
    	oHeader.setStrFirstName("Kishore");
    	oHeader.setStrLastName("Kishore");
    	oHeader.setStrOrderNo("1");
    	oHeader.setStrPaymentInfo("Card");
    	
        when(orderHeaderInfo.findOne("1")).thenReturn(oHeader);
        when(orderHeaderInfo.findOne("2")).thenReturn(null);
    } 
	
	@Test
	public void testValidateUniqueOrderNo(){
		
		//test the validateUniqueOrderNo functionality
		Assert.assertEquals(false,orderService.validateUniqueOrderNo("1"));
		Assert.assertEquals(true,orderService.validateUniqueOrderNo("2"));
	}

	@Test
	public void testCreateNewOrder(){
		
		Order orderLineNotUnique = new Order();
		List<OrderLine> lOrderLine = new ArrayList<OrderLine>();

		orderLineNotUnique.setStrFirstName("Kishore");
		orderLineNotUnique.setStrLastName("Karthik");
		orderLineNotUnique.setStrOrderNo("2");
		orderLineNotUnique.setStrPaymentInfo("Credit Card");
		orderLineNotUnique.setStrShipZipCode("11111");
		
		OrderHeader orderHeader = new OrderHeader();
		orderHeader.setStrFirstName(orderLineNotUnique.getStrFirstName());
		orderHeader.setStrLastName(orderLineNotUnique.getStrLastName());
		orderHeader.setStrOrderNo(orderLineNotUnique.getStrOrderNo());
		orderHeader.setStrPaymentInfo(orderLineNotUnique.getStrPaymentInfo());
		orderHeader.setStrShipZipCode(orderLineNotUnique.getStrShipZipCode());

		//Test the OrderLines not availability
		Assert.assertEquals("Order Lines not available",orderService.createNewOrder(orderLineNotUnique));

		OrderLine ol1 = new OrderLine();
		ol1.setOrder(orderHeader);
		ol1.setStrOrderLineNo("1");
		ol1.setStrItemId("1111");
		ol1.setStrQuantity("1");
		
		OrderLine ol2 = new OrderLine();
		ol2.setOrder(orderHeader);;
		ol2.setStrOrderLineNo("1");
		ol2.setStrItemId("1111");
		ol2.setStrQuantity("1");
		
		lOrderLine.add(ol1);
		orderLineNotUnique.setlOrderLine(lOrderLine);

		//Test valid order creation
		Assert.assertEquals(null, orderService.createNewOrder(orderLineNotUnique));
		
		lOrderLine.add(ol2);
		orderLineNotUnique.setlOrderLine(lOrderLine);

		//test the Order Lines no unique functionality
		Assert.assertEquals("Order Lines is not unique",orderService.createNewOrder(orderLineNotUnique));

	}
}
