package com.sixfootplus.blog.taglib.renderer

import java.rmi.server.UID
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Formatter
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.plugins.PluginManagerHolder
import org.apache.commons.codec.digest.DigestUtils
import java.util.regex.*

/*
 *
 * @author Andreas Schmitt
 */
class RenderUtils {

    public static String getUniqueId() {
        return DigestUtils.md5Hex(new UID().toString())
    }
}