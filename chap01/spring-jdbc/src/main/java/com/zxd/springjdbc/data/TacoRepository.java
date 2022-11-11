package com.zxd.springjdbc.data;

import com.zxd.springjdbc.model.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
