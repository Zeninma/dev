Mainly about what I found about Jython:
* Double and Float. The get method of PyList will automatically does type coercion of its elements, doing __tojava__(Object.class). However, such solution has the problem that it cannot tell the difference from float to double.
