<?php

namespace GroupUse1;

function test1() {
}
function test2() {
}

namespace GroupUse2;

use function GroupUse1\{
    test1,/*FORMAT_START*/test2/*FORMAT_END*/
};
