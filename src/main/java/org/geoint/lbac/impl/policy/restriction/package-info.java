/**
 * Security restrictions are an implementation detail that is intentionally
 * hidden from the public LBAC API. Just as with any implementation class,
 * application-level manipulation of the implementation classes, despite their
 * immutability, may result in undesired behavior.
 *
 * Security restrictions are added to security policies to enforce runtime label
 * requirements. Restrictions ensure that labels remain valid during the
 * construction on the {@link SecurityMarking}, and through this, ensures that
 * every constructed instance of {@link SecurityMarking} is valid for the policy
 * it has been constructed.
 */
package org.geoint.lbac.impl.policy.restriction;
