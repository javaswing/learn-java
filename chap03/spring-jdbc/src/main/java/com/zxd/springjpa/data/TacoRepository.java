package com.zxd.springjpa.data;

import com.zxd.springjpa.model.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
