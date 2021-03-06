package com.darly.snbc.jarlib.common.bolts.links;

import android.net.Uri;

import com.darly.snbc.jarlib.common.bolts.tasks.Task;


/**
 * Implement this interface to provide an alternate strategy for resolving App Links that may
 * include pre-fetching, caching, or querying for App Link data from an index provided by a
 * service provider.
 */
public interface AppLinkResolver {
  /**
   * Asynchronously resolves App Link data for a given URL.
   *
   * @param url the URL to resolve into an App Link.
   * @return the {@link AppLink} for the given URL.
   */
  public Task<AppLink> getAppLinkFromUrlInBackground(Uri url);
}
