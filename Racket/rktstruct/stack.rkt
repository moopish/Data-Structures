#lang racket

; Stack structure
; Michael van Dyk
; May 10th 2017

;  Should allow for values to be added to the top of the stack and
; remove the values from the top of the stack. FIFO queue.

(define (stack)
  ; The head of the stack
  (define head '())

  ; Used to perform 'a' and 'b'
  (define (get-set a b) a)

  ; Checks to see if the stack is empty.
  ; Returns true or false.
  (define (in-empty?) (empty? head))

  ; Returns the first value in the stack.
  (define (peek)
    (if (in-empty?)
        '()
        (car head)))

  ; Removes the first value in the stack and returns it.
  (define (pop)
    (if (in-empty?)
        '()
        (get-set (car head)
                 (set! head
                       (cdr head)))))

  ; Adds the value 'e' to the stack
  (define (push e)
    (set! head (cons e head)))

  ; Return the number of elements in the stack
  (define (size)
    (define (size-it count curr)
      (if (empty? curr)
          count
          (size-it (+ count 1) (cdr curr))))
    (size-it 0 head))


  ; Allow for methods to be run on this structure
  (define (dispatch msg)
    (cond ((eq? msg 'push) push)
          ((eq? msg 'peek) (peek))
          ((eq? msg 'pop) (pop))
          ((eq? msg 'empty?) (in-empty?))
          ((eq? msg 'size) (size))
          (else (error "Method does not exist:" msg))))

  dispatch)

(provide stack)