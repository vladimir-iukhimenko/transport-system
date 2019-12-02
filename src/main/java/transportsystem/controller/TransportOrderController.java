package transportsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.TransportOrder;
import transportsystem.service.TransportOrderService;

import java.util.List;

/**
 * Created by DZCKJS0 on 02.12.2019.
 */
@Controller
public class TransportOrderController {
    private TransportOrderService transportOrderService;

    @Autowired
    public void setTransportOrderService(TransportOrderService transportOrderService) {
        this.transportOrderService = transportOrderService;
    }

    @RequestMapping(value = "/transportorders", method = RequestMethod.GET)
    public ModelAndView listTransportOrders()
    {
        List<TransportOrder> transportOrders = transportOrderService.getAllTransportOrders();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportorders");
        modelAndView.addObject("transportorders", transportOrders);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/add", method = RequestMethod.GET)
    public ModelAndView addTransportOrder()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editortransportorders");
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/add", method = RequestMethod.POST)
    public ModelAndView addTransportOrder(@ModelAttribute("transportorder") TransportOrder transportOrder)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportorders");
        transportOrderService.addTransportOrder(transportOrder);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTransportOrder(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        TransportOrder transportOrder = transportOrderService.getTransportOrderById(id);
        modelAndView.setViewName("editortransportorders");
        modelAndView.addObject("transportorder", transportOrder);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/edit", method = RequestMethod.POST)
    public ModelAndView editTransportOrder(@ModelAttribute("transportorder") TransportOrder transportOrder)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportorders");
        transportOrderService.editTransportOrder(transportOrder);
        return modelAndView;
    }

    @RequestMapping(value = "/transportorders/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTransportOrder(@PathVariable("id") int id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportorders");
        TransportOrder transportOrder = transportOrderService.getTransportOrderById(id);
        transportOrderService.deleteTransportOrder(transportOrder);
        return modelAndView;
    }
}
