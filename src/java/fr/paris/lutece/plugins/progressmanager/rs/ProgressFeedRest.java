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
package fr.paris.lutece.plugins.progressmanager.rs;

import fr.paris.lutece.plugins.progressmanager.service.ProgressManagerService;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.util.json.ErrorJsonResponse;
import fr.paris.lutece.util.json.JsonResponse;
import fr.paris.lutece.util.json.JsonUtil;

import org.apache.log4j.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * ProgressFeedRest
 */
@Path( RestConstants.BASE_PATH + Constants.API_PATH + Constants.VERSION_PATH + Constants.PROGRESSFEED_PATH )
public class ProgressFeedRest
{
    private static final int VERSION_1 = 1;
    private final Logger _logger = Logger.getLogger( RestConstants.REST_LOGGER );

    /**
     * Get Progress feed status
     * 
     * @param nVersion
     *            the API version
     * @param strId
     * @return the ProgressFeed
     */
    @GET
    @Path( Constants.ID_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getProgressStatus( @PathParam( Constants.VERSION ) Integer nVersion, @PathParam( Constants.ID ) String strId )
    {
        if ( nVersion == VERSION_1 )
        {
            return getProgressFeedV1( strId );
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) ).build( );
    }

    /**
     * Get ProgressFeed V1
     * 
     * @param id
     *            the id
     * @return the ProgressFeed for the version 1
     */
    private Response getProgressFeedV1( String strId )
    {
        ProgressManagerService _service = ProgressManagerService.getInstance( );
        if ( _service == null || !_service.isRegistred( strId ) )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }

        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( ProgressManagerService.getInstance( ).getProgressStatus( strId ) ) ) ).build( );
    }

    /**
     * Get success nb
     * 
     * @param nVersion
     *            the API version
     * @param strId
     * @return the ProgressFeed
     */
    @GET
    @Path( Constants.ID_PATH + Constants.SUCCESS_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getSuccessNb( @PathParam( Constants.VERSION ) Integer nVersion, @PathParam( Constants.ID ) String strId )
    {
        if ( nVersion == VERSION_1 )
        {
            return getSuccessNbV1( strId );
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) ).build( );
    }

    /**
     * Get success nb V1
     * 
     * @param id
     *            the id
     * @return the ProgressFeed for the version 1
     */
    private Response getSuccessNbV1( String strId )
    {
        ProgressManagerService _service = ProgressManagerService.getInstance( );
        if ( _service == null || !_service.isRegistred( strId ) )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }

        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( ProgressManagerService.getInstance( ).getSuccessNb( strId ) ) ) ).build( );
    }

    /**
     * Get success nb
     * 
     * @param nVersion
     *            the API version
     * @param strId
     * @return the ProgressFeed
     */
    @GET
    @Path( Constants.ID_PATH + Constants.FAILURE_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getFailureNb( @PathParam( Constants.VERSION ) Integer nVersion, @PathParam( Constants.ID ) String strId )
    {
        if ( nVersion == VERSION_1 )
        {
            return getFailureNbV1( strId );
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) ).build( );
    }

    /**
     * Get failure nb V1
     * 
     * @param id
     *            the id
     * @return the ProgressFeed for the version 1
     */
    private Response getFailureNbV1( String strId )
    {
        ProgressManagerService _service = ProgressManagerService.getInstance( );
        if ( _service == null || !_service.isRegistred( strId ) )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }

        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( ProgressManagerService.getInstance( ).getFailureNb( strId ) ) ) ).build( );
    }

    /**
     * Get report
     * 
     * @param nVersion
     *            the API version
     * @param strId
     * @return the ProgressFeed
     */
    @GET
    @Path( Constants.ID_PATH + Constants.REPORT_PATH )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getReport( @PathParam( Constants.VERSION ) Integer nVersion, @PathParam( Constants.ID ) String strId )
    {
        if ( nVersion == VERSION_1 )
        {
            return getReportV1( strId );
        }
        _logger.error( Constants.ERROR_NOT_FOUND_VERSION );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_VERSION ) ) ).build( );
    }

    /**
     * Get failure nb V1
     * 
     * @param id
     *            the id
     * @return the ProgressFeed for the version 1
     */
    private Response getReportV1( String strId )
    {
        ProgressManagerService _service = ProgressManagerService.getInstance( );
        if ( _service == null || !_service.isRegistred( strId ) )
        {
            _logger.error( Constants.ERROR_NOT_FOUND_RESOURCE );
            return Response.status( Response.Status.NOT_FOUND )
                    .entity( JsonUtil.buildJsonResponse( new ErrorJsonResponse( Response.Status.NOT_FOUND.name( ), Constants.ERROR_NOT_FOUND_RESOURCE ) ) )
                    .build( );
        }

        return Response.status( Response.Status.OK )
                .entity( JsonUtil.buildJsonResponse( new JsonResponse( ProgressManagerService.getInstance( ).getReport( strId ) ) ) ).build( );
    }
}
