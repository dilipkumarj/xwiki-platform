/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.gwt.wysiwyg.client.plugin.sync;

import org.xwiki.component.annotation.ComponentRole;
import org.xwiki.gwt.wysiwyg.client.diff.Revision;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface used on the server.
 * 
 * @version $Id$
 */
@ComponentRole
@RemoteServiceRelativePath("SyncService.gwtrpc")
public interface SyncService extends RemoteService
{
    /**
     * Synchronizes this editor with others that edit the same page.
     * 
     * @param syncedRevision the changes to this editor's content, since the last update
     * @param pageName the page being edited
     * @param version the version affected by syncedRevision
     * @param syncReset resets the sync server for this page
     * @return the result of synchronizing this editor with others editing the same page
     */
    SyncResult syncEditorContent(Revision syncedRevision, String pageName, int version, boolean syncReset);
}
