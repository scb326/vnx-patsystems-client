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

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Vector;

/**
 * <h1>SessionNode</h1>
 * <p>This is the top level node in the treeview which is not displayed but simply stores all
 * the information about the first level nodes. It is also a reference point for where the
 * nodes will hang off.</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class SessionNode implements TreeNode
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    protected ClientAPI clientAPI;
    /**
     * a vector of all of the first level of nodes which will always be of type <code>Exchange</code>
     */
    protected Vector<TreeNode> exchangeNodes;

    /**
     * constructor
     *
     * @param clientAPI
     */
    public SessionNode(ClientAPI clientAPI)
    {
        this.clientAPI = clientAPI;

    }

    /**
     * returns the child node at the specified index.
     *
     * @param childIndex
     */
    public TreeNode getChildAt(int childIndex)
    {

        if (exchangeNodes == null)
        {
            exchangeNodes = new Vector<TreeNode>();
            Enumeration keys = clientAPI.exchangeMap.keys();
            for (int x = 0; x < clientAPI.exchangeMap.size(); x++)
            {
                Exchange exchange = clientAPI.exchangeMap.get(keys.nextElement());
                exchangeNodes.addElement(new ExchangeNode(clientAPI, this, exchange));
            }
        }
        return exchangeNodes.elementAt(childIndex);
    }

    /**
     * returns the number of children for that node
     */
    public int getChildCount()
    {
        return clientAPI.exchangeMap.size();
    }

    /**
     * returns the parent of the node
     */
    public TreeNode getParent()
    {
        return null;
    }

    /**
     * gets the absolute index of the tree node
     *
     * @param node
     */
    public int getIndex(TreeNode node)
    {
        return 0;
    }

    /**
     * this is always true as all nodes will be allowed to have children
     */
    public boolean getAllowsChildren()
    {
        return true;
    }

    /**
     * this will always be false as no nodes will be leaves
     */
    public boolean isLeaf()
    {
        return false;
    }

    /**
     * this will always return null
     *
     */
    public Enumeration children()
    {
        return null;
    }
}
