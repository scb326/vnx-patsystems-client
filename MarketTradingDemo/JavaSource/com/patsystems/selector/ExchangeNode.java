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


package com.patsystems.selector;

import patsystems.api.delphi.ClientAPI;
import patsystems.api.delphi.Exchange;
import patsystems.api.delphi.Commodity;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Vector;

/**
 * <h1>ExchangeNode</h1>
 * <p>Contains the data for an exchange node</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class ExchangeNode implements TreeNode
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    protected ClientAPI clientAPI;
    protected SessionNode sessionNode;
    protected Exchange exchange;
    protected Vector<TreeNode> commodityNodes;

    /**
     * Constructor
     *
     * @param clientAPI
     * @param sessionNode
     * @param exchange
     */
    public ExchangeNode(ClientAPI clientAPI, SessionNode sessionNode, Exchange exchange)
    {
        this.clientAPI = clientAPI;
        this.sessionNode = sessionNode;
        this.exchange = exchange;
    }

    /**
     * returns the childnode at the given index
     *
     * @param childIndex
     */
    public TreeNode getChildAt(int childIndex)
    {

        return commodityNodes.elementAt(childIndex);
    }

    /**
     * returns the number of children for that exchange
     */
    public int getChildCount()
    {

        if (commodityNodes == null)
        {
            commodityNodes = new Vector<TreeNode>();
            Enumeration<String> keys = clientAPI.commodityMap.keys();
            for (int x = 0; x < clientAPI.commodityMap.size(); x++)
            {
                Commodity commodity = clientAPI.commodityMap.get(keys.nextElement());
                if (commodity.exchange == exchange)
                    commodityNodes.addElement(new CommodityNode(clientAPI, sessionNode, commodity));
            }
        }

        return commodityNodes.size();
    }

    /**
     * returns the root node of the treeview as all exchanges will hang off of the root node
     */
    public TreeNode getParent()
    {
        return sessionNode;
    }

    /**
     * returns the index of the given node
     *
     * @param node
     */
    public int getIndex(TreeNode node)
    {
        return 0;
    }

    /**
     * always returns true as all nodes will be allowed to have children
     */
    public boolean getAllowsChildren()
    {
        return true;
    }

    /**
     * always returns false
     */
    public boolean isLeaf()
    {
        return false;
    }

    /**
     * always returns null
     */
    public Enumeration children()
    {
        return null;
    }

    /**
     * returns the exchange name
     */
    public String toString()
    {
        return exchange.name;
    }
}
