#lang racket

; Michael van Dyk
; May 10th 2017

(require "queue.rkt")
(require "stack.rkt")

(define (queue-tester que)
  (begin
    (println (que 'size))
    (println (que 'empty?))
    ((que 'add) 1)
    (println (que 'size))
    ((que 'add) 2)
    (println (que 'size))
    (println (que 'empty?))
    (println (que 'get))
    (println (que 'remove))
    (println (que 'size))
    (println (que 'get))
    (println (que 'remove))
    (println (que 'size))))

(queue-tester (queue))