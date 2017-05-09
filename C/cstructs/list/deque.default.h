
//START OF DEFAULTS

inline
bool ${STRUCT}_push (${STRUCT} * deque, void * item)
{
    return ${STRUCT}_add_first(deque, item);
}

inline
void * ${STRUCT}_peek (${STRUCT} * deque)
{
    return ${STRUCT}_get_first(deque);
}

inline
void * ${STRUCT}_pop (${STRUCT} * deque)
{
    return ${STRUCT}_remove_first(deque);
}

inline
bool ${STRUCT}_add (${STRUCT} * deque, void * item)
{
    return ${STRUCT}_add_last(deque, item);
}

inline
void * ${STRUCT}_get (${STRUCT} * deque)
{
    return ${STRUCT}_get_first(deque);
}

inline
void * ${STRUCT}_remove (${STRUCT} * deque)
{
    return ${STRUCT}_remove_first(deque);
}

//END OF DEFAULTS
