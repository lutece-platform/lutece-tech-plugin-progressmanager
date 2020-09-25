/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.progressmanager.web;

import fr.paris.lutece.plugins.progressmanager.business.ProgressFeed;
import fr.paris.lutece.plugins.progressmanager.service.ProgressManagerService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;

/**
 * This class provides the user interface to manage Agent features ( manage )
 */
@Controller( controllerJsp = "ManageProgressFeeds.jsp", controllerPath = "jsp/admin/plugins/progressmanager/", right = "PROGRESSMANAGER_MANAGEMENT" )
public class ProgressManagerJspBean extends MVCAdminJspBean
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 237753177212082626L;

    // Templates
    private static final String TEMPLATE_MANAGE_PROGRESS_FEEDS = "/admin/plugins/progressmanager/manage_progress_feeds.html";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_PROGRESS_FEEDS = "progressmanager.manage_feeds.pageTitle";

    // Markers
    private static final String MARK_FEED_LIST = "feed_list";

    // Views
    private static final String VIEW_MANAGE_PROGRESS_FEEDS = "manageProgressFeeds";


    /**
     * Build the Manage View
     *
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_PROGRESS_FEEDS, defaultView = true )
    public String getManageProgressFeeds( HttpServletRequest request )
    {
        ProgressManagerService service = ProgressManagerService.getInstance( );
        
        Map<String, ProgressFeed> feedMap = service.getProgressFeeds( );
        
        Map<String, Object> model = new HashMap<>( );
        model.put( MARK_FEED_LIST, feedMap );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_PROGRESS_FEEDS, TEMPLATE_MANAGE_PROGRESS_FEEDS, model );
    }
}
