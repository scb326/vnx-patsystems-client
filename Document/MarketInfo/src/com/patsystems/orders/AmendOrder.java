/*************************************************************************************************
 ************************************** SOFTWARE DISCLAIMER **************************************
 *************************************************************************************************

 --------------------------------------IMPORTANT NOTICE-------------------------------------------

 1.	The Software is provided 'as is' without warranty of any kind, either express or implied,
    including, but not limited to, the implied warranties of fitness for a purpose, or the
    warranty of non-infringement.
 2.	Without limiting the foregoing, Patsystems makes no warranty that:
    (a)	the software will meet your requirements
    (b)	the software will be uninterrupted, timely, secure or error-free
    (c)	the results that may be obtained from the use of the software will be effective,
        accurate or reliable
    (d)	the quality of the software will meet your expectations
    (e)	any errors in the software obtained from Patsystems web site will be corrected.
 3.	Further the Software and its documentation made available:
    •	could include technical or other mistakes, inaccuracies or typographical errors.
        Patsystems may make changes to the software or documentation at its discretion.
    •	may be out of date, and Patsystems makes no commitment to update such materials.
 4.	Patsystems assumes no responsibility for errors or omissions in the software or
    documentation available from its web site and the software.
 5.	To the fullest extend permittable by law, in no event shall Patsystems be liable to you
    or kind, or any damages whatsoever, including, without limitation, those resulting from
    loss of use, data or profits, whether or not Patsystems has been advised of the possibility
    of such damages, and on any theory of liability, arising out of or in connection with the
    use of this software.
 6.	The use of the software is done at your own discretion and risk and with agreement that
    you will be solely responsible for any damage to your computer system or loss of data
    that results from such activities. No advice or information, whether oral or written,
    obtained by you from Patsystems or from Patsystems web site shall create any warranty
    for the software.

/*************************************************************************************************
 *************************************** COPYRIGHT NOTICE ****************************************
 *************************************************************************************************

 The disclosure, copying or distribution of the Software is prohibited and may be unlawful. The
 contents of the Software are copyright © Patsystems UK Limited. All rights are expressly reserved.

 Any content printed or otherwise may not be sold, licensed, transferred, copied or reproduced in
 whole or in part in any manner or in or on any media to any person without the prior written
 consent of Patsystems UK Limited.

 *************************************************************************************************/


package com.patsystems.orders;

import patsystems.api.delphi.ClientAPI;
import patsystems.api.delphi.Order;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Enumeration;

/**
 * <h1>AmendOrder</h1>
 * <p>Allows the user to amend an existing order</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class AmendOrder extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    protected JButton buttonAmend;
    protected JButton buttonCancel;
    protected ClientAPI clientAPI;
    protected JComboBox comboOrderIds;
    protected JLabel labelExchangeValue;
    protected JLabel labelCommodityValue;
    protected JLabel labelContractValue;
    protected JLabel labelAccountValue;
    protected JLabel labelOrderTypeValue;
    protected JLabel labelSideValue;
    protected JTextField textPrice;
    protected JTextField textLots;

    /**
     * Constructor
     *
     * @param clientAPI
     */
    public AmendOrder(ClientAPI clientAPI)
    {
        this.clientAPI = clientAPI;
    }

    /**
     * Initialises the amend order ticket
     */
    public void init()
    {
        setTitle("Amend Order");

        comboOrderIds = new JComboBox();
        comboOrderIds.addActionListener(this);

        JLabel labelOrderIds = new JLabel("Order IDs");
        JLabel labelExchange = new JLabel("Exchange");
        labelExchangeValue = new JLabel("");
        JLabel labelCommodity = new JLabel("Commodity");
        labelCommodityValue = new JLabel("");
        JLabel labelContract = new JLabel("Contract");
        labelContractValue = new JLabel("");
        JLabel labelAccount = new JLabel("Account");
        labelAccountValue = new JLabel("");
        JLabel labelOrderType = new JLabel("Order Type");
        labelOrderTypeValue = new JLabel("");
        JLabel labelSide = new JLabel("Side");
        labelSideValue = new JLabel("");
        JLabel labelPrice = new JLabel("Price");
        JLabel labelLots = new JLabel("Lots");
        textPrice = new JTextField();
        textLots = new JTextField();

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(10, 2));
        panelTop.add(labelOrderIds);
        panelTop.add(comboOrderIds);
        panelTop.add(labelExchange);
        panelTop.add(labelExchangeValue);
        panelTop.add(labelCommodity);
        panelTop.add(labelCommodityValue);
        panelTop.add(labelContract);
        panelTop.add(labelContractValue);
        panelTop.add(labelAccount);
        panelTop.add(labelAccountValue);
        panelTop.add(labelOrderType);
        panelTop.add(labelOrderTypeValue);
        panelTop.add(labelSide);
        panelTop.add(labelSideValue);
        panelTop.add(labelPrice);
        panelTop.add(textPrice);
        panelTop.add(labelLots);
        panelTop.add(textLots);

        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        buttonAmend = new JButton("Amend");
        buttonAmend.addActionListener(this);
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(this);
        panelBottom.add(buttonAmend);
        panelBottom.add(buttonCancel);

        populateOrderIds();

        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }

    /**
     * populates the <code>Order ID</code> combo box
     */
    private void populateOrderIds()
    {

        comboOrderIds.removeAllItems();
        Enumeration keys = clientAPI.orderMap.keys();
        while (keys.hasMoreElements())
            comboOrderIds.addItem(keys.nextElement());
    }

    /**
     * Populates the ticket with the order information corresponding to the selected order id
     */
    private void populateScreen()
    {
        Order order = clientAPI.orderMap.get(comboOrderIds.getSelectedItem());
        labelExchangeValue.setText(order.exchange);
        labelCommodityValue.setText(order.commodity);
        labelContractValue.setText(order.contract);
        labelAccountValue.setText(order.trader);
        labelOrderTypeValue.setText(order.orderType);
        labelSideValue.setText(order.side);
        textPrice.setText(order.price);
        textLots.setText(String.valueOf(order.actualLots));

    }

    /**
     * creates an amend request and sends it to the JavaWrapper
     *
     * @throws Exception
     */
    private void sendAmendRequest() throws Exception
    {
        //WE NEED TO CHECK THAT THE LOTS VALUE IS AN INTEGER
        try
        {

            int lots = Integer.parseInt(textLots.getText());
            String account = labelAccountValue.getText();
            String exchange = labelExchangeValue.getText();
            String commodity = labelCommodityValue.getText();
            String contract = labelContractValue.getText();
            String price = textPrice.getText();
            String orderId = (String) comboOrderIds.getSelectedItem();

            AmendRequest amendRequest = new AmendRequest(account, orderId, exchange, commodity, contract, price, lots);

            clientAPI.doAmendOrder(amendRequest.orderID, amendRequest.buffer);

        }
        catch (NumberFormatException nx)
        {
            JOptionPane.showMessageDialog(this, "Enter a valid integer for the number of lots");
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == comboOrderIds)
            populateScreen();
        else
            if (e.getSource() == buttonAmend)
            {
                try
                {
                    sendAmendRequest();
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                    clientAPI.logFile.addMessage(e1.getMessage());
                }

            }
            else
                if (e.getSource() == buttonCancel)
                    dispose();

    }
}
