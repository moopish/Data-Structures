#lang racket

; Queue structure
; Michael van Dyk
; May 10th 2017

; Does not work

(define (queue)
  (define head '())
  (define tail '())

  ; Used to perform 'a' and 'b'
  (define (get-set a b) a)
  
  ; Checks to see if the stack is empty.
  ; Returns true or false.
  (define (in-empty?) (empty? head))

  (define (node val nxt)
    (cons val nxt))

  (define (add e)
    (if (empty? tail)
        (begin
          (set! tail (node e '()))
          (set! head tail))
        (set! tail (node (car tail) (node e '())))))

  ; Returns the first value in the stack.
  (define (get)
    (if (in-empty?)
        '()
        (car head)))

    ; Removes the first value in the stack and returns it.
  (define (remove)
    (if (in-empty?)
        '()
        (get-set (car head)
                 (set! head
                       (cdr head)))))
  

  ; Return the number of elements in the stack
  (define (size)
    (define (size-it count curr)
      (if (empty? curr)
          count
          (size-it (+ count 1) (cdr curr))))
    (size-it 0 head))

  ; Allow for methods to be run on this structure
  (define (dispatch msg)
    (cond ((eq? msg 'add) add)
          ((eq? msg 'get) (get))
          ((eq? msg 'remove) (remove))
          ((eq? msg 'empty?) (in-empty?))
          ((eq? msg 'size) (size))
          (else (error "Method does not exist:" msg))))

  dispatch)

(provide queue)