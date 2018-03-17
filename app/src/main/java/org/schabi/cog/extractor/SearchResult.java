package org.schabi.cog.extractor;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

/*
 * Copyright 2015 Levit Nudi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class SearchResult {
    public static SearchResult getSearchResult(SearchEngine engine, String query,
                                               int page, String languageCode, Downloader dl)
            throws ExtractionException, IOException {

        SearchResult result = engine.search(query, page, languageCode, dl).getSearchResult();
        if(result.resultList.isEmpty()) {
            if(result.suggestion.isEmpty()) {
                throw new ExtractionException("Empty result despite no error");
            } else {
                // This is used as a fallback. Do not relay on it !!!
                throw new SearchEngine.NothingFoundException(result.suggestion);
            }
        }
        return result;
    }

    public String suggestion = "";
    public List<StreamPreviewInfo> resultList = new Vector<>();
    public List<Exception> errors = new Vector<>();
}