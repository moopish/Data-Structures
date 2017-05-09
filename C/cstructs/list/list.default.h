
//START OF DEFAULTS

inline
bool ${STRUCT}_push (${STRUCT} * list, void * item)
{
    return ${STRUCT}_add_first(list, item);
}

inline
void * ${STRUCT}_peek (${STRUCT} * list)
{
    return ${STRUCT}_get_first(list);
}

inline
void * ${STRUCT}_pop (${STRUCT} * list)
{
    return ${STRUCT}_remove_first(list);
}

inline
bool ${STRUCT}_add (${STRUCT} * list, void * item)
{
    return ${STRUCT}_add_last(list, item);
}

inline
void * ${STRUCT}_get (${STRUCT} * list)
{
    return ${STRUCT}_get_first(list);
}

inline
void * ${STRUCT}_remove (${STRUCT} * list)
{
    return ${STRUCT}_remove_first(list);
}

inline
bool ${STRUCT}_add_first (${STRUCT} * list, void * item)
{
    return ${STRUCT}_add_index(list, item, 0);
}

inline
void * ${STRUCT}_get_first (${STRUCT} * list)
{
    return ${STRUCT}_get_index(list, 0);
}

inline
void * ${STRUCT}_remove_first (${STRUCT} * list)
{
    return ${STRUCT}_remove_index(list, 0);
}

inline
bool ${STRUCT}_add_last (${STRUCT} * list, void * item)
{
    return ${STRUCT}_add_index(list, item, ${STRUCT}_size(list));
}

inline
void * ${STRUCT}_get_last (${STRUCT} * list)
{
    return ${STRUCT}_get_index(list, ${STRUCT}_size(list) - 1);
}

inline
void * ${STRUCT}_remove_last (${STRUCT} * list)
{
    return ${STRUCT}_remove_index(list, ${STRUCT}_size(list) - 1);
}

//END OF DEFAULTS
