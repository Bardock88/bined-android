/*
 * Copyright (C) ExBin Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.exbin.auxiliary.binary_data.jna.paged;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.exbin.auxiliary.binary_data.EditableBinaryData;
import org.exbin.auxiliary.binary_data.jna.JnaBufferData;
import org.exbin.auxiliary.binary_data.buffer.paged.BufferPagedData;
import org.exbin.auxiliary.binary_data.paged.DataPageCreator;

/**
 * Paged data stored using JNA byte buffer.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class JnaBufferPagedData extends BufferPagedData {

    public JnaBufferPagedData() {
    }

    public JnaBufferPagedData(DataPageCreator dataPageCreator) {
        this.dataPageCreator = dataPageCreator;
    }

    public JnaBufferPagedData(int pageSize) {
        this.pageSize = pageSize;
    }

    @Nonnull
    @Override
    protected JnaBufferData createNewPage(byte[] pageData) {
        if (dataPageCreator != null) {
            EditableBinaryData page = dataPageCreator.createPage(pageData.length);
            page.replace(0, pageData);
            return (JnaBufferData) page;
        }

        return new JnaBufferData(pageData);
    }

    @Nonnull
    @Override
    protected JnaBufferData createNewPage(int pageDataSize) {
        if (dataPageCreator != null) {
            return (JnaBufferData) dataPageCreator.createPage(pageDataSize);
        }

        return new JnaBufferData(pageDataSize);
    }

    @Nullable
    @Override
    public DataPageCreator getDataPageCreator() {
        return dataPageCreator;
    }

    @Override
    public void setDataPageCreator(@Nullable DataPageCreator dataPageCreator) {
        this.dataPageCreator = dataPageCreator;
    }
}
