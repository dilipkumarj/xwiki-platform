/*
 * Copyright 2006, XpertNet SARL, and individual contributors as indicated
 * by the contributors.txt.
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
 *
 * @author ludovic
 * @author sdumitriu
 */
package com.xpn.xwiki.web;

import com.xpn.xwiki.XWiki;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;

public class DeleteAction extends XWikiAction
{
    public boolean action(XWikiContext context) throws XWikiException
    {
        XWiki xwiki = context.getWiki();
        XWikiRequest request = context.getRequest();
        XWikiResponse response = context.getResponse();
        XWikiDocument doc = context.getDoc();
        if (doc.isNew()) {
            return true;
        }
        String confirm = request.getParameter("confirm");
        if ((confirm != null) && (confirm.equals("1"))) {
            String language = xwiki.getLanguagePreference(context);
            if ((language == null) || (language.equals("")) ||
                language.equals(doc.getDefaultLanguage()))
            {
                xwiki.deleteAllDocuments(doc, context);
            } else {
                // Only delete the translation
                XWikiDocument tdoc = doc.getTranslatedDocument(language, context);
                xwiki.deleteDocument(tdoc, context);
            }
            String redirect = Utils.getRedirect(request, null);
            if (redirect != null) {
                sendRedirect(response, redirect);
                return false;
            }
        }
        return true;
    }

    public String render(XWikiContext context) throws XWikiException
    {
        XWikiRequest request = context.getRequest();
        String confirm = request.getParameter("confirm");
        if ((confirm != null) && (confirm.equals("1"))) {
            return "deleted";
        } else {
            return "delete";
        }
    }
}
