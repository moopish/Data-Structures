copy(dot)bf May 11th 2017 Michael van Dyk

for generic case need to know how many shifts from
source byte to destination byte (sd)
destination byte to temp byte   (dt)
temp byte to source byte        (ts)
source byte to temp byte        (st)

,  assign 12 to first byte

.>.<    print both and move back

        let us copy 12 to another memory location

>[-]    ensure the destination is empty
>[-]<<  ensure the temp is empty

        start copy

[
 >+     shift pointer over and inc shift of sd 
 >+     temp value shift of dt
 <<-    shift back and dec shift of ts
]

>>      shift of st
[
 -
 <<     shift of ts
 +
 >>     shift of st
]       get the first back to it's original value

        end copy

<<
.>.     print both memory locations
