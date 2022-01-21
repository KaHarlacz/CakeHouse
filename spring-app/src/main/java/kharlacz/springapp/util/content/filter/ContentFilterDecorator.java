package kharlacz.springapp.util.content.filter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ContentFilterDecorator implements ContentFilter {
    
    protected final ContentFilter wrappedFilter;
    
}
