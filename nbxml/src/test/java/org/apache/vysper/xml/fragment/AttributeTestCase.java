/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.vysper.xml.fragment;

import junit.framework.TestCase;
import org.mockito.Mockito;
import org.mockito.ReturnValues;

/**
 */
public class AttributeTestCase extends TestCase {

    public void testEquals() {
        Attribute att1 = new Attribute("foo", "bar");
        Attribute att2 = new Attribute("foo", "bar");

        assertTrue(att1.equals(att2));
        assertTrue(att2.equals(att1));
    }

    public void testNotEquals() {
        Attribute att1 = new Attribute("foo", "bar");
        Attribute att2 = new Attribute("foo", "dummy");

        assertFalse(att1.equals(att2));
        assertFalse(att2.equals(att1));
    }

    public void testEqualsNamespaceUri() {
        Attribute att1 = new Attribute("http://example.com", "foo", "bar");
        Attribute att2 = new Attribute("http://example.com", "foo", "bar");

        assertTrue(att1.equals(att2));
        assertTrue(att2.equals(att1));
    }

    public void testEqualsDifferentNamespaceUri() {
        Attribute att1 = new Attribute("http://example.com", "foo", "bar");
        Attribute att2 = new Attribute("http://someother.com", "foo", "bar");

        assertFalse(att1.equals(att2));
        assertFalse(att2.equals(att1));
    }

    public void testDefaultReturnValues() {
        Attribute att1 = Mockito.mock(Attribute.class, (ReturnValues) invocationOnMock -> {
            if (invocationOnMock.getMethod().getName().equals("getName")) {
                return "foo";
            } else if (invocationOnMock.getMethod().getName().equals("getValue")) {
                return "bar";
            } else {
                return null;
            }
        });

        // Call methods
        String namespaceUri = att1.getNamespaceUri();
        String name = att1.getName();
        String value = att1.getValue();

        // Assert the default return values
        assertNull(namespaceUri);
        assertEquals("foo", name);
        assertEquals("bar", value);
    }

    public void testEqualsNullNamespaceUri() {
        Attribute att1 = new Attribute("http://example.com", "foo", "bar");
        Attribute att2 = new Attribute("foo", "bar");

        assertFalse(att1.equals(att2));
        assertFalse(att2.equals(att1));
    }
}
